package com.soa.firstserviceejb.service

import FilteringOperation
import com.soa.common.dto.MusicBandDto
import com.soa.common.dto.MusicGenre
import com.soa.common.dto.NewMusicBandDto
import com.soa.firstserviceejb.model.musicBand.converter.MusicBandConverter
import com.soa.firstserviceejb.model.musicBand.converter.NewMusicBandConverter
import com.soa.firstserviceejb.model.musicBand.entity.MusicBand
import com.soa.firstserviceejb.model.musicBand.entity.MusicBandArray
import com.soa.firstserviceejb.model.musicBand.entity.MusicBandId
import com.soa.firstserviceejb.model.musicBand.repository.MusicBandRepository
import com.soa.firstserviceejb.model.other.converter.CoordinatesConverter
import com.soa.firstserviceejb.model.other.converter.LocationConverter
import com.soa.firstserviceejb.model.other.converter.PersonConverter
import com.soa.firstserviceejb.util.Filter
import com.soa.firstserviceejb.util.ResultList
import com.soa.firstserviceejb.util.Sort
import jakarta.ejb.Remote
import jakarta.ejb.Stateless
import jakarta.inject.Inject
import org.jboss.ejb3.annotation.Pool
import java.util.*
import java.util.regex.Pattern

@Remote
interface MusicBandService {
    fun saveBand(newBand: NewMusicBandDto): MusicBandDto
    fun getBandById(bandId: MusicBandId): MusicBandDto
    fun updateBand(bandId: MusicBandId, newBandDto: NewMusicBandDto): MusicBandDto
    fun deleteBand(bandId: MusicBandId)
    fun getAllBands(sortList: List<String>, filterList: List<String>, _page: Int?, _pageSize: Int?): MusicBandArray?
    fun getSumParticipants(): Long
    fun getMinParticipants(participantsNumber: Int): Long
    fun searchGenre(genre: MusicGenre): List<MusicBand>
}

@Stateless
@Pool("slsb-strict-max-pool")
open class MusicBandServiceImpl: MusicBandService {

    @Inject
    private lateinit var musicBandRepository: MusicBandRepository

    private var newMusicBandConverter: NewMusicBandConverter = NewMusicBandConverter(PersonConverter(LocationConverter()), CoordinatesConverter())

    private var musicBandConverter: MusicBandConverter = MusicBandConverter(PersonConverter(LocationConverter()), CoordinatesConverter())

    override fun saveBand(newBand: NewMusicBandDto): MusicBandDto {
        println("addBand - service")
        val band = newMusicBandConverter.toEntity(newBand)
        return musicBandConverter.toDto(musicBandRepository.save(band))
    }

    override fun getBandById(bandId: MusicBandId): MusicBandDto {
        val band = musicBandRepository.findById(bandId)
        return musicBandConverter.toDto(band)
    }

    override fun updateBand(bandId: MusicBandId, newBandDto: NewMusicBandDto): MusicBandDto {
        val band = getBandById(bandId)
        val newBand = newMusicBandConverter.toEntity(newBandDto)
        newBand.id = band.id
        newBand.creationDate = band.creationDate
        return musicBandConverter.toDto(musicBandRepository.update(newBand))
    }

    override fun deleteBand(bandId: MusicBandId) {
        val band = musicBandRepository.findById(bandId)
        musicBandRepository.deleteById(band)
    }

    override fun getAllBands(sortList: List<String>, filterList: List<String>, _page: Int?, _pageSize: Int?): MusicBandArray? {
        println("jopa in start service")
        var page = _page
        var pageSize = _pageSize
        if (page != null && pageSize == null) pageSize = 10
        if (pageSize != null && page == null) page = 1
        if (page != null && page == 0) page = 1

        val nestedFieldNamePattern = Pattern.compile("(.*)\\.(.*)")
        val lhsPattern = Pattern.compile("(.*)\\[(.*)\\]=(.*)")

        val sorts: MutableList<Sort> = ArrayList()

        if (sortList.isNotEmpty()) {
            val containsOppositeSorts = sortList.stream().allMatch { e1 ->
                sortList.stream().allMatch { e2 ->
                    e1 == "-$e2"
                }
            }
            require(!containsOppositeSorts) { "Request contains opposite sort parameters" }
            for (sort in sortList) {
                val desc = sort.startsWith("-")
                var sortFieldName =
                    if (desc) sort.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1] else sort
                var nestedName: String? = null
                val matcher = nestedFieldNamePattern.matcher(sortFieldName)
                if (matcher.find()) {
                    val nestedField =
                        matcher.group(2).substring(0, 1).lowercase(Locale.getDefault()) + matcher.group(2).substring(1)
                    sortFieldName = matcher.group(1)
                    nestedName = nestedField
                }
                sorts.add(
                    Sort(desc, sortFieldName, nestedName)
                )
            }
        }

        val filters: MutableList<Filter> = ArrayList<Filter>()

        for (filter in filterList) {
            val matcher = lhsPattern.matcher(filter)
            var fieldName: String? = null
            var fieldValue: String? = null
            var filteringOperation: FilteringOperation? = null
            var nestedName: String? = null
            if (matcher.find()) {
                fieldName = matcher.group(1)
                val nestedFieldMatcher = nestedFieldNamePattern.matcher(fieldName!!)
                if (nestedFieldMatcher.find()) {
                    val nestedField = nestedFieldMatcher.group(2).substring(0, 1)
                        .lowercase(Locale.getDefault()) + nestedFieldMatcher.group(2).substring(1)
                    fieldName = nestedFieldMatcher.group(1)
                    nestedName = nestedField
                }
                filteringOperation = FilteringOperation.fromValue(matcher.group(2))
                if (fieldName == "musicGenre") {
                    require(!(filteringOperation != FilteringOperation.EQ && filteringOperation != FilteringOperation.NEQ)) { "Only [eq] and [neq] operations are allowed for \"musicGenre\" field" }
                }
                fieldValue = if (fieldName == "view") {
                    matcher.group(3).lowercase(Locale.getDefault())
                } else matcher.group(3)
            }
            require(fieldName!!.isNotEmpty()) { "Filter field name is empty" }
            require(fieldValue!!.isNotEmpty()) { "Filter field value is empty" }
            require(filteringOperation != FilteringOperation.UNDEFINED) { "No or unknown filtering operation. Possible values are: eq,neq,gt,lt,gte,lte." }
            filters.add(
                Filter(fieldName, nestedName, filteringOperation, fieldValue)
            )
        }

        println("jopa in end service")
        val entityPage: MusicBandArray = try {
            musicBandRepository.test(sorts, filters, page, pageSize)
        } catch (e: NullPointerException) {
            return null
        } catch (e: NumberFormatException) {
            return null
        }
        println("THE END")
        val tmp = ResultList("12", entityPage, 200)
        println(tmp.toString())
        return tmp.entityPage
    }

    override fun getSumParticipants() =
        musicBandRepository.findSumNumberParticipantsAllRecords()

    override fun getMinParticipants(participantsNumber: Int) =
        musicBandRepository.countMusicBandsByNumberOfParticipantsIsLessThan(participantsNumber)

    override fun searchGenre(genre: MusicGenre) =
        musicBandRepository.findMusicBandsByMusicGenreIsLessThan(genre)
}