package co.gotoinc.ui.view.airports

import android.os.Handler
import android.os.Looper
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import co.gotoinc.core.entity.Airport
import co.gotoinc.domain.book_flight.AirPortsInteractor
import co.gotoinc.domain.live.LiveFlightEntity
import co.gotoinc.ui.base.BaseViewModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AirportsViewModel(
    private val liveFlightEntity: LiveFlightEntity,
    private val airportInteractor: AirPortsInteractor
) : BaseViewModel() {

    fun setOrigin(airport: Airport) = liveFlightEntity.updateOrigin(airport)
    fun setDestination(airport: Airport) = liveFlightEntity.updateDestination(airport)

    fun observeAirports() = airportInteractor.getAirports()

    var airports: List<Airport>? = null

    fun getSubList(start: Int, end: Int): List<Airport> {
        airports?.let {
            logger.log("start: $start ; size: $end ")
            return if (it.isNotEmpty()) it.subList(
                start,
                if (it.size + 1 < end) it.size else end
            )
            else listOf()
        }
        return listOf()
    }

    private class MainExecutor : Executor {
        override fun execute(command: Runnable) {
            Handler(Looper.getMainLooper()).post(command)
        }
    }

    fun getPagedList(): PagedList<Airport> {
        return PagedList.Builder(
            dataSource, PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(30)
                .setInitialLoadSizeHint(0)
                .build()
        ).setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainExecutor())
            .build()
    }

    fun searchAirports(query: String = "") {
        airportInteractor.getAirports(query)
    }

    val dataSource by lazy {
        object : PositionalDataSource<Airport>() {
            override fun loadInitial(
                params: LoadInitialParams,
                callback: LoadInitialCallback<Airport>
            ) {
                val subList = getSubList(
                    params.requestedStartPosition,
                    params.requestedStartPosition + params.pageSize
                )
                callback.onResult(subList, 0)
            }

            override fun loadRange(
                params: LoadRangeParams,
                callback: LoadRangeCallback<Airport>
            ) {
                callback.onResult(
                    getSubList(
                        params.startPosition,
                        params.startPosition + params.loadSize
                    )
                )
            }
        }
    }
}