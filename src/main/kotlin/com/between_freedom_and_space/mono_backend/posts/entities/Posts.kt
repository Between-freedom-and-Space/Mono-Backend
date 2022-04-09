package com.between_freedom_and_space.mono_backend.posts.entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Posts(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<Posts>(PostsTable)

    var text by PostsTable.text

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Posts

        if (id.value != other.id.value) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.value.hashCode()
        result = 31 * result + text.hashCode()
        return result
    }


}
