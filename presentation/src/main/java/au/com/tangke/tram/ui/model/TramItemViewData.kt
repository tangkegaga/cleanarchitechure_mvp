package au.com.tangke.tram.ui.model

data class TramItemViewData(var destination: String? = null,

                            var predictedArrival: String? = null,

                            var routeNo: String? = null,

                            var predictedLeftTime: String? = null)