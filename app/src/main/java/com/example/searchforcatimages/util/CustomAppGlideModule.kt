package com.example.searchforcatimages.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class CustomAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(createOkHttpClient()))
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
//    private fun createOkHttpClient(): OkHttpClient {
//        return OkHttpClient().newBuilder()
//            .addInterceptor { chain ->
//                var request = chain.request()
//                request = request.newBuilder()
//                    .header("Authorization", "Client-ID YOUR_CLIENT_ID")
//                    .build()
//                chain.proceed(request)
//            }
//            .build()
//    }

/*
    class RequestListener : com.bumptech.glide.request.RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            Log.e("CustomAppGlideModule", "Erro ao baixar imagem: $e")
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            Log.d("CustomAppGlideModule", "Imagem baixada com sucesso!")
            return false
        }
    }
*/
}