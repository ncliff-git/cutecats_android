package com.github.ncliff.cutecats.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ncliff.cutecats.data.network.CatBreeds
import com.github.ncliff.cutecats.data.network.NetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedCatApiViewModel : ViewModel() {
    private val _breedsList = MutableLiveData<List<CatBreeds>>()
    val breedsList: LiveData<List<CatBreeds>> = _breedsList
    private val _breeds = MutableLiveData<CatBreeds>()
    val breeds: LiveData<CatBreeds> = _breeds
    private val corScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun getBreedsList(onFail: () -> Unit) {
        corScope.launch {
            NetworkService.getInstance()
                .getJSONApi()
                .getCatBreedsList()
                .enqueue(object : Callback<List<CatBreeds>> {
                    override fun onResponse(
                        call: Call<List<CatBreeds>>,
                        response: Response<List<CatBreeds>>
                    ) {
                        _breedsList.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<CatBreeds>>, t: Throwable) {
                        onFail()
                    }

                }
                )
        }
    }

    fun findBreedsByName(name: String, onFail: () -> Unit) {
        corScope.launch {
            NetworkService.getInstance()
                .getJSONApi()
                .getCatBreedsByName(name)
                .enqueue(
                    object : Callback<List<CatBreeds>> {
                        override fun onResponse(
                            call: Call<List<CatBreeds>>,
                            response: Response<List<CatBreeds>>
                        ) {
                            _breeds.postValue(response.body()?.get(0))
                        }

                        override fun onFailure(call: Call<List<CatBreeds>>, t: Throwable) {
                            onFail()
                        }

                    }
                )
        }
    }
}
