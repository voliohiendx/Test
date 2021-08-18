package volio.tech.sharefile.framework.presentation.send.image.foldes

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.framework.presentation.send.image.adapter.FolderImageParentAdapter

fun FolderImageFragment.initRv(dataLocal: DataLocal) {
    folderImageParentAdapter = FolderImageParentAdapter(dataLocal, viewLifecycleOwner.lifecycleScope)
    binding.rvParentFolder.apply {
        layoutManager = LinearLayoutManager(context)
        setHasFixedSize(true)
    }
    binding.rvParentFolder.adapter = folderImageParentAdapter
    Log.d("HienOcaaa", dataLocal.folder.toString())
    folderImageParentAdapter!!.submitList(dataLocal.folder)
}
