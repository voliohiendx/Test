package volio.tech.sharefile.framework.presentation.hiendx

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import volio.tech.sharefile.R
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.TransferFile
import volio.tech.sharefile.databinding.HiendxFragmentBinding
import volio.tech.sharefile.framework.presentation.common.BaseFragment
import volio.tech.sharefile.util.setPreventDoubleClick
import java.io.File

@AndroidEntryPoint
class HienDxFragment(
    private val glide: RequestManager
) : BaseFragment<HiendxFragmentBinding>(HiendxFragmentBinding::inflate) {

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let {

            }
        }
    }

    override fun init(view: View) {
        // openFile()
        context?.let {
            dataLocalViewModel.getDocuments()
            dataLocalViewModel.getApps(it)
            dataLocalViewModel.getImages()
            dataLocalViewModel.getMusics()
            dataLocalViewModel.getVideos()
            dataLocalViewModel.getAPKs()
            dataLocalViewModel.getZipRar()
        }
        Log.d("hienocs0806", getFreeInternalMemory().toString())
        Log.d("hienocs0806", getFreeSystemMemory().toString())
        binding.tvNext.setPreventDoubleClick {
            safeNav(R.id.hienDxFragment,HienDxFragmentDirections.actionHienDxFragmentToSendFragment2())
        }
    }

    override fun subscribeObserver(view: View) {
        dataLocalViewModel.allImage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
               // Log.d("hienocs0806Image", it.file.size.toString())
                if(it.file.size>2250){
                    val listFileTransfer = mutableListOf<FileModel>()
                    listFileTransfer.add(it.file[0])
                    listFileTransfer.add(it.file[1])
                    listFileTransfer.add(it.file[2])
                    transferViewModel.addTransfer(listFileTransfer) {
                        it?.let {
                            Log.d("hienocs0806Image", it.toString())
                            transferViewModel.getTransferByTokenPort("hienDxTest"){
                                it?.let {
                                    Log.d("hienocs0806Image", it.toString())
                                }
                            }
                        }
                    }
                }
            }
        })

        dataLocalViewModel.allVideo.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                Log.d("hienocs0806Video", it.file.toString())
            }
        })

        dataLocalViewModel.allDocument.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                Log.d("hienocs0806Document", it.file.toString())
            }
        })

        dataLocalViewModel.allApp.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                Log.d("hienocs0806App", it.file.size.toString())
            }
        })

        dataLocalViewModel.allMusic.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                Log.d("hienocs0806Music", it.file.size.toString())
            }
        })

        dataLocalViewModel.allAPK.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                Log.d("hienocs0806APK", it.file.size.toString())
            }
        })


        dataLocalViewModel.allOther.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.let {
                Log.d("hienocs0806Other", it.file.size.toString())
            }
        })
    }

    //dung lượng còn trống GB
    fun getFreeInternalMemory(): Float {
        return getFreeMemory(Environment.getDataDirectory()).toFloat() / 1024 / 1024 / 1024
    }

    //dung lượng cua thiet bi GB
    fun getFreeSystemMemory(): Long {
        return getFreeMemory(Environment.getRootDirectory())
    }

    // Get free space for provided path
    // Note that this will throw IllegalArgumentException for invalid paths
    fun getFreeMemory(path: File): Long {
        val stats = StatFs(path.absolutePath)
        return stats.availableBlocksLong * stats.blockSizeLong
    }

    fun openFile() {
        val mimeTypes = arrayOf(
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
            "application/vnd.ms-powerpoint",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
            "application/pdf",
        )

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
        }

        intent.type = if (mimeTypes.size === 1) mimeTypes[0] else "*/*"
        if (mimeTypes.isNotEmpty()) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        }
        startForResult.launch(intent)
    }


}