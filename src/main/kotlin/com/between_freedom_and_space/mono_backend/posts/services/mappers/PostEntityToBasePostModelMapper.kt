package com.between_freedom_and_space.mono_backend.posts.services.mappers

import com.between_freedom_and_space.mono_backend.common.components.ModelMapper
import com.between_freedom_and_space.mono_backend.posts.entities.post.Post
import com.between_freedom_and_space.mono_backend.posts.services.models.BasePostModel

class PostEntityToBasePostModelMapper: ModelMapper<Post, BasePostModel> {

    override fun map(original: Post): BasePostModel {
        return BasePostModel(
            id = original.id.value,
            text = original.text,
            authorId = original.author.value,
            commentsIds = original.comments.copy().map { it.id.value },
            reactionsIds = original.reactions.copy().map { it.id.value },
            tagsIds = original.tags.copy().map { it.id.value },
            isVisible = original.isVisible,
            isDeleted = original.isDeleted,
            isEdited = original.isEdited,
            createdDate = original.createdDate,
            updatedDate = original.updatedDate,
        )
    }
}