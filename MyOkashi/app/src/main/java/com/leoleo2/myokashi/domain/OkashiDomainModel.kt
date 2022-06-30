package com.leoleo2.myokashi.domain

import com.leoleo2.myokashi.APIResponse

data class OkashiDomainModel(val id: Int, val name: String, val url: String, val image: String?)

/*
Web APIのレスポンスをdomain Modelに変換する拡張関数。
データソース側の変更(使うWeb APIが変わった or APIの仕様変更が入った時に、
UI, domainレイヤーまで仕様変更の影響を受けずに済むのでこのようなクラスを作成することが多いが、
単純なアプリの場合はこのようなdomainレイヤーの処理は作成しない。
 */
fun APIResponse.toOkashiDomainModels(): List<OkashiDomainModel>? {
    return this.item?.map {
        OkashiDomainModel(id = it.id, name = it.name, url = it.url, image = it.image)
    }
}
