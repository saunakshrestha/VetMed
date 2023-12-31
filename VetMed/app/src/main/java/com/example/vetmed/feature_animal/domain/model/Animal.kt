package com.example.vetmed.feature_animal.domain.model

import com.example.vetmed.feature_animal.util.toRealmInstant
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.time.Instant

open class Animal : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var owner_id: String = ""
    var animalName: String = ""
    var description: String = ""
    var images: RealmList<String> = realmListOf()
    var date: RealmInstant = Instant.now().toRealmInstant()
}