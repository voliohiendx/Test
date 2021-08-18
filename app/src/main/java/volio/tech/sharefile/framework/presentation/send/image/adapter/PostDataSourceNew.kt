package volio.tech.sharefile.framework.presentation.send.image.adapter

import androidx.paging.PagingSource
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.Folder

class PostDataSourceNew(private val list: List<Folder>) : PagingSource<Int, Folder>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Folder> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val responseData = mutableListOf<Folder>()
            responseData.addAll(list)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}