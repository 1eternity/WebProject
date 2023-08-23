package com.example.webproject.entity

class GameBean {

    var name:String=""
    var documentUrl:String = ""
    var gameUrl:String = ""
    var kuaiShouUrl:String = ""
    var icon:Int = 0

    constructor(name: String, documentUrl: String, gameUrl: String) {
        this.name = name
        this.documentUrl = documentUrl
        this.gameUrl = gameUrl
    }

    constructor(name: String, documentUrl: String,kuaiShouUrl:String, gameUrl: String, icon: Int) {
        this.name = name
        this.documentUrl = documentUrl
        this.kuaiShouUrl = kuaiShouUrl
        this.gameUrl = gameUrl
        this.icon = icon
    }

}