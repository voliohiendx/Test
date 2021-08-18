package volio.tech.sharefile.framework.presentation.send.image.allitem

import androidx.recyclerview.widget.LinearLayoutManager
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.framework.presentation.send.image.adapter.AllImageParentAdapter

fun AllImageFragment.initRv(dataLocal:DataLocal) {
    imageParentAdapter = AllImageParentAdapter(dataLocal)
    binding.rvParentImage.apply {
        layoutManager = object : LinearLayoutManager(context, VERTICAL, false) {}
        setHasFixedSize(true)
    }
    binding.rvParentImage.adapter = imageParentAdapter
    imageParentAdapter!!.submitList(dataLocal.listDate)
}
