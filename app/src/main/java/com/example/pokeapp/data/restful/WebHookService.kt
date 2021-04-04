package com.example.pokeapp.data.restful

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


/***
 * interface that makes it possible to post pokemon information on a web hook server
 * Webhook url used: https://webhook.site/bce3b1a0-be56-46f3-8267-b2d2e2d58a38/pokemon/{POKEMON_NAME}
 */

interface WebHookService {

    @Headers("Content-Type: application/json")
    @POST("bce3b1a0-be56-46f3-8267-b2d2e2d58a38/pokemon/{name}")
    fun addPokemon(@Body json: String, @Path("name") name: String) : Call<ResponseBody?>
}