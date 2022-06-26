package com.github.ncliff.cutecats.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ncliff.cutecats.data.model.*
import com.github.ncliff.cutecats.data.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedCatApiViewModel : ViewModel() {
    private val _breedsList = MutableLiveData<List<CatBreeds>>()
    val breedsList: LiveData<List<CatBreeds>> = _breedsList
    private val _breeds = MutableLiveData<CatBreeds>()
    val breeds: LiveData<CatBreeds> = _breeds
    private val _favList = MutableLiveData<List<CatFav>>()
    val favList: LiveData<List<CatFav>> = _favList
    private val corScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun getBreedsList(onFail: () -> Unit) {
        corScope.launch {
            Log.d("Network", "Download CatBreeds list")
            NetworkService.getInstance()
                .getJSONApi()
                .getCatBreedsList()
                .enqueue(object : Callback<List<CatBreeds>> {
                    override fun onResponse(
                        call: Call<List<CatBreeds>>,
                        response: Response<List<CatBreeds>>
                    ) {
                        Log.d("Network", "CatBreeds list downloaded")
                        _breedsList.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<CatBreeds>>, t: Throwable) {
                        Log.e("Network", "CatBreeds list download fail")
                        onFail()
                    }

                }
                )
        }
    }

    fun findBreedsByName(name: String, onFail: () -> Unit) {
        corScope.launch {
            Log.d("Network", "Download CatBreeds")
            NetworkService.getInstance()
                .getJSONApi()
                .getCatBreedsByName(name)
                .enqueue(
                    object : Callback<List<CatBreeds>> {
                        override fun onResponse(
                            call: Call<List<CatBreeds>>,
                            response: Response<List<CatBreeds>>
                        ) {
                            Log.d("Network", "CatBreeds downloaded")
                            _breeds.postValue(response.body()?.get(0))
                        }

                        override fun onFailure(call: Call<List<CatBreeds>>, t: Throwable) {
                            Log.e("Network", "CatBreeds download fail")
                            onFail()
                        }

                    }
                )
        }
    }

    fun getRandomCatImage(onFail: () -> Unit): LiveData<CatImage> {
        val catImageLiveData = MutableLiveData<CatImage>()
        corScope.launch {
            NetworkService.getInstance()
                .getJSONApi()
                .getRandomCatImage(1)
                .enqueue(object : Callback<List<CatImage>> {
                    override fun onResponse(
                        call: Call<List<CatImage>>,
                        response: Response<List<CatImage>>
                    ) {
                        Log.d("Network", "CatImage downloaded")
                        catImageLiveData.postValue(response.body()?.get(0))
                    }

                    override fun onFailure(call: Call<List<CatImage>>, t: Throwable) {
                        Log.e("Network", "CatImage download fail")
                        onFail()
                    }

                })
        }
        return catImageLiveData
    }

    fun getCatFavList(onFail: () -> Unit) {
        corScope.launch {
            NetworkService.getInstance()
                .getJSONApi()
                .getCatFavList()
                .enqueue(object : Callback<List<CatFav>> {
                    override fun onResponse(
                        call: Call<List<CatFav>>,
                        response: Response<List<CatFav>>
                    ) {
                        Log.d(
                            "Network",
                            "CatFav downloaded list size: ${response.body()?.size ?: "None"}"
                        )
                        _favList.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<CatFav>>, t: Throwable) {
                        Log.e("Network", "CatFav download fail")
                        onFail()
                    }
                })
        }
    }

    fun postSaveImageAsFavourites(image: CatFav, onFail: () -> Unit) {
        corScope.launch {
            NetworkService.getInstance()
                .getJSONApi()
                .postSaveImageAsFavourites(image)
                .enqueue(object : Callback<CatResponses> {
                    override fun onResponse(
                        call: Call<CatResponses>,
                        response: Response<CatResponses>
                    ) {
                        Log.d(
                            "Network",
                            "Cat added on favourites. BODY: ${response.errorBody()?.string()}"
                        )
                    }

                    override fun onFailure(call: Call<CatResponses>, t: Throwable) {
                        Log.e("Network", "CatResponses fail")
                        onFail()
                    }

                })
        }
    }

    fun postCatVote(imageVote: CatVote, onFail: () -> Unit) {
        corScope.launch {
            NetworkService.getInstance()
                .getJSONApi()
                .postCatVote(imageVote = imageVote)
                .enqueue(object : Callback<CatResponses> {
                    override fun onResponse(
                        call: Call<CatResponses>,
                        response: Response<CatResponses>
                    ) {
                        Log.d("Network", "Voted. BODY: ${response.body()}")
                    }

                    override fun onFailure(call: Call<CatResponses>, t: Throwable) {
                        Log.e("Network", "Vote fail")
                        onFail()
                    }
                })
        }
    }

    fun postCatUpload(file: RequestBody, onFail: () -> Unit) {
        corScope.launch {
            NetworkService.getInstance()
                .getJSONApi()
                .postCatUpload(file)
                .enqueue(
                    object : Callback<UploadResponse> {
                        override fun onResponse(
                            call: Call<UploadResponse>,
                            response: Response<UploadResponse>
                        ) {
                            if (response.errorBody() != null) {
                                Log.e(
                                    "Network",
                                    "Image upload error error body: ${
                                        response.errorBody()?.string()
                                    }"
                                )
                            } else {
                                Log.d(
                                    "Network",
                                    "Image upload ${response.body()} "
                                )
                            }
                        }

                        override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                            Log.e("Network", "Image upload error: $t")
                            onFail()
                        }

                    }
                )
        }
    }
}
