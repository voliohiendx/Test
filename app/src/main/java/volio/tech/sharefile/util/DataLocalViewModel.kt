package volio.tech.sharefile.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import volio.tech.sharefile.business.domain.DataLocal
import volio.tech.sharefile.business.domain.DateSelect
import volio.tech.sharefile.business.domain.FileModel
import volio.tech.sharefile.business.domain.FileModel.Companion.IS_IMAGE
import volio.tech.sharefile.business.domain.Folder
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class DataLocalViewModel
@Inject
constructor(
    @ApplicationContext private val application: Context
) : ViewModel() {

    val formatter = SimpleDateFormat("dd/MM/yyyy")

    companion object {
        const val CHECK_ITEM_LOADING = 50
    }

    private val _allImage = MutableLiveData<DataLocal>()
    val allImage: LiveData<DataLocal>
        get() = _allImage

    private val _allVideo = MutableLiveData<DataLocal>()
    val allVideo: LiveData<DataLocal>
        get() = _allVideo

    private val _allMusic = MutableLiveData<DataLocal>()
    val allMusic: LiveData<DataLocal>
        get() = _allMusic

    private val _allDocument = MutableLiveData<DataLocal>()
    val allDocument: LiveData<DataLocal>
        get() = _allDocument

    private val _allApp = MutableLiveData<DataLocal>()
    val allApp: LiveData<DataLocal>
        get() = _allApp

    private val _allAPK = MutableLiveData<DataLocal>()
    val allAPK: LiveData<DataLocal>
        get() = _allAPK

    private val _allOther = MutableLiveData<DataLocal>()
    val allOther: LiveData<DataLocal>
        get() = _allOther

    private val projection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        arrayOf(
            MediaStore.Files.FileColumns.MEDIA_TYPE,
            MediaStore.Files.FileColumns.RELATIVE_PATH,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME,
            MediaStore.Files.FileColumns.BUCKET_ID,
        )
    else arrayOf(
        MediaStore.Files.FileColumns.MEDIA_TYPE,
        MediaStore.Files.FileColumns.DATA,
        MediaStore.Files.FileColumns.TITLE,
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME,
        MediaStore.Files.FileColumns.BUCKET_ID,
    )

    private val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

    private val uri = MediaStore.Files.getContentUri("external")

    fun getImages() {
        val arrMedia = ArrayList<FileModel>()
        val arrFolder = ArrayList<Folder>()
        val folders = HashMap<Long, String>()
        val arrDate = ArrayList<DateSelect>()
        val dateMap = HashMap<String, String>()
        CoroutineScope(Dispatchers.Default).launch {
            var check = 0

            val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                    + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)

            val cursor =
                application.contentResolver.query(
                    uri,
                    projection,
                    selection,
                    null,
                    sortOrder
                )
            if (cursor != null && cursor.count > 0) {

                while (cursor.moveToNext()) {
                    try {
                        val idMedia: Long =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                        val nameMedia: String =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                            } else {
                                cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.TITLE
                                    )
                                )
                            }

                        val path = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            "sdcard/" + cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.RELATIVE_PATH)
                            ) + cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                            )
                        } else {
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                            )
                        }

                        val contentUri = Uri.withAppendedPath(uri, "" + idMedia)
                        val folderIdIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
                        val folderNameIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME)
                        val folderId: Long = cursor.getLong(folderIdIndex)
                        val timeModified = File(path).lastModified()

                        var timeFile = "14/05/2021"
                        try {
                            timeFile = formatter.format(timeModified)
                        } catch (e: Exception) {
                        }

                        if (File(path).exists() && File(path).length() > 5000) {
                            if (!folders.containsKey(folderId)) {
                                val folderName: String = cursor.getString(folderNameIndex)
                                val folder = Folder(folderId, folderName, 1)
                                folders[folderId] = folderName
                                arrFolder.add(folder)
                            } else {
                                arrFolder.find {
                                    it.id == folderId
                                }?.let {
                                    it.size = it.size + 1
                                }
                            }
                            arrMedia.add(
                                FileModel(
                                    id = idMedia,
                                    idFolder = folderId,
                                    name = nameMedia,
                                    uri = contentUri.toString(),
                                    path = path,
                                    timeCreated = timeModified,
                                    type = FileModel.IS_IMAGE
                                )
                            )

                            if (!dateMap.containsKey(timeFile)) {
                                arrDate.add(
                                    DateSelect(
                                        date = timeFile,
                                        type = IS_IMAGE,
                                        time = timeModified,
                                        listIdFolder = arrayListOf(folderId)
                                    )
                                )
                                dateMap[timeFile] = timeFile
                            }

                            check++
                            if (check == CHECK_ITEM_LOADING) {
                                check = 0
                                withContext(Dispatchers.Main) {
                                    _allImage.postValue(
                                        DataLocal(
                                            file = arrMedia,
                                            folder = arrFolder,
                                            listDate = arrDate
                                        )
                                    )

                                }
                            }
                        }
                    } catch (ex: Exception) {
                    }
                }
                cursor.close()
                folders.clear()
                dateMap.clear()
            }
            withContext(Dispatchers.Main) {
                _allImage.postValue(
                    DataLocal(
                        file = arrMedia,
                        folder = arrFolder,
                        listDate = arrDate
                    )
                )
            }
        }
    }

    fun getVideos() {
        val arrMedia = ArrayList<FileModel>()
        val arrFolder = ArrayList<Folder>()
        val folders = HashMap<Long, String>()
        val arrDate = ArrayList<DateSelect>()
        val dateMap = HashMap<String, String>()
        CoroutineScope(Dispatchers.Default).launch {
            var check = 0

            val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                    + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

            val uri = MediaStore.Files.getContentUri("external")
            val cursor =
                application.contentResolver.query(
                    uri,
                    projection,
                    selection,
                    null,
                    sortOrder
                )
            if (cursor != null && cursor.count > 0) {

                while (cursor.moveToNext()) {
                    try {
                        val idMedia: Long =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                        val nameMedia: String =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                            } else {
                                cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.TITLE
                                    )
                                )
                            }

                        val path = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            "sdcard/" + cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.RELATIVE_PATH)
                            ) + cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                            )
                        } else {
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                            )
                        }

                        val contentUri = Uri.withAppendedPath(uri, "" + idMedia)
                        val folderIdIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
                        val folderNameIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME)
                        val folderId: Long = cursor.getLong(folderIdIndex)
                        val timeModified = File(path).lastModified()



                        if (File(path).exists() && File(path).length() > 5000) {
                            if (!folders.containsKey(folderId)) {
                                val folderName: String = cursor.getString(folderNameIndex)
                                val folder = Folder(folderId, folderName, 1)
                                folders[folderId] = folderName
                                arrFolder.add(folder)
                            } else {
                                arrFolder.find {
                                    it.id == folderId
                                }?.let {
                                    it.size = it.size + 1
                                }
                            }
                            arrMedia.add(
                                FileModel(
                                    id = idMedia,
                                    idFolder = folderId,
                                    name = nameMedia,
                                    uri = contentUri.toString(),
                                    path = path,
                                    timeCreated = timeModified,
                                    type = FileModel.IS_VIDEO,
                                    duration = File(path).getMediaDuration(context =  application)
                                )
                            )

                            var timeFile = "14/05/2021"
                            try {
                                timeFile = formatter.format(timeModified)
                            } catch (e: Exception) {
                            }

                            if (!dateMap.containsKey(timeFile)) {
                                arrDate.add(
                                    DateSelect(
                                        date = timeFile,
                                        type = IS_IMAGE,
                                        time = timeModified,
                                        listIdFolder = arrayListOf(folderId)
                                    )
                                )
                                dateMap[timeFile] = timeFile
                            }

                            check++
                            if (check == CHECK_ITEM_LOADING) {
                                check = 0
                                withContext(Dispatchers.Main) {
                                    _allVideo.postValue(
                                        DataLocal(
                                            file = arrMedia,
                                            folder = arrFolder,
                                            listDate = arrDate
                                        )
                                    )
                                }
                            }

                        }

                    } catch (ex: Exception) {
                    }
                }
                cursor.close()
                folders.clear()
                dateMap.clear()
            }
            withContext(Dispatchers.Main) {
                _allVideo.postValue(
                    DataLocal(
                        file = arrMedia,
                        folder = arrFolder,
                        listDate = arrDate
                    )
                )
            }
        }
    }

    fun getMusics() {
        val arrMedia = ArrayList<FileModel>()
        val arrFolder = ArrayList<Folder>()
        val folders = HashMap<Long, String>()
        CoroutineScope(Dispatchers.Default).launch {
            var check = 0

            val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                    + MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO)

            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

            val uri = MediaStore.Files.getContentUri("external")
            val cursor =
                application.contentResolver.query(
                    uri,
                    projection,
                    selection,
                    null,
                    sortOrder
                )
            if (cursor != null && cursor.count > 0) {

                while (cursor.moveToNext()) {
                    try {
                        val idMedia: Long =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                        val nameMedia: String =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                            } else {
                                cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.TITLE
                                    )
                                )
                            }

                        val path = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            "sdcard/" + cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.RELATIVE_PATH)
                            ) + cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                            )
                        } else {
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                            )
                        }

                        val contentUri = Uri.withAppendedPath(uri, "" + idMedia)
                        val folderIdIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
                        val folderNameIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME)
                        val folderId: Long = cursor.getLong(folderIdIndex)
                        val timeModified = File(path).lastModified()

                        if (File(path).exists() && File(path).length() > 5000) {
                            if (!folders.containsKey(folderId)) {
                                val folderName: String = cursor.getString(folderNameIndex)
                                val folder = Folder(folderId, folderName, 1)
                                folders[folderId] = folderName
                                arrFolder.add(folder)
                            } else {
                                arrFolder.find {
                                    it.id == folderId
                                }?.let {
                                    it.size = it.size + 1
                                }
                            }
                            arrMedia.add(
                                FileModel(
                                    id = idMedia,
                                    idFolder = folderId,
                                    name = nameMedia,
                                    uri = contentUri.toString(),
                                    path = path,
                                    timeCreated = timeModified,
                                    type = FileModel.IS_MUSIC,
                                    duration = 0L
                                )
                            )
                            check++
                            withContext(Dispatchers.Main) {
                                if (check == CHECK_ITEM_LOADING) {
                                    check = 0
                                    _allMusic.postValue(
                                        DataLocal(
                                            file = arrMedia,
                                            folder = arrFolder
                                        )
                                    )
                                }
                            }
                        }

                    } catch (ex: Exception) {
                    }
                }
                cursor.close()
                folders.clear()
            }
            withContext(Dispatchers.Main) {

                _allMusic.postValue(
                    DataLocal(
                        file = arrMedia,
                        folder = arrFolder
                    )
                )
            }
        }
    }

    fun getDocuments() {
        val arrMedia = ArrayList<FileModel>()
        val arrFolder = ArrayList<Folder>()
        val folders = HashMap<Long, String>()

        CoroutineScope(Dispatchers.Default).launch {
            var check = 0
            val uri = MediaStore.Files.getContentUri("external")
            application.contentResolver.query(
                uri,
                projection,
                null,
                null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    try {
                        val idMedia: Long =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                        val nameMedia: String =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                val name = cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                                val index = name.lastIndexOf(".")
                                if (index != -1) {
                                    val nameCut = name.substring(0, index)
                                    nameCut
                                } else {
                                    name
                                }
                            } else cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    MediaStore.Files.FileColumns.TITLE
                                )
                            )

                        val path =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                                "sdcard/" + cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.RELATIVE_PATH
                                    )
                                ) + cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                            else cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                            )

                        val contentUri = Uri.withAppendedPath(uri, "" + idMedia)
                        val folderIdIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
                        val folderNameIndex: Int = cursor.getColumnIndexOrThrow(
                            MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME
                        )
                        val folderId: Long = cursor.getLong(folderIdIndex)
                        val timeModified = File(path).lastModified()

                        val sp = path.split(".")
                        var typeMedia = FileModel.PDF
                        var typeMediaDetail = FileModel.PDF

                        if (!sp.isNullOrEmpty()) {
                            typeMediaDetail = sp[sp.size - 1].trim()
                            typeMedia = if (typeMediaDetail == "pdf") FileModel.PDF
                            else if (typeMediaDetail == "ppt" || typeMediaDetail == "pptx") FileModel.POWERPOINT
                            else if (typeMediaDetail == "doc" || typeMediaDetail == "docx") FileModel.WORD
                            else if (typeMediaDetail == "xls" || typeMediaDetail == "xlsx") FileModel.EXCEL
                            else continue
                        }

                        if (File(path).exists() && File(path).length() > 1000) {
                            if (!folders.containsKey(folderId)) {
                                val folderName: String = cursor.getString(folderNameIndex)
                                val folder = Folder(folderId, folderName, 0)
                                folders[folderId] = folderName
                                arrFolder.add(folder)
                            } else {
                                arrFolder.find {
                                    it.id == folderId
                                }?.let {
                                    it.size = it.size + 1
                                }
                            }

                            arrMedia.add(
                                FileModel(
                                    id = idMedia,
                                    idFolder = folderId,
                                    name = nameMedia,
                                    uri = contentUri.toString(),
                                    path = path,
                                    timeCreated = timeModified,
                                    type = FileModel.IS_DOC,
                                    typeDoc = typeMedia,
                                    duration = 0L
                                )
                            )

                            check++
                            if (check == CHECK_ITEM_LOADING) {
                                check = 0
                                withContext(Dispatchers.Main) {
                                    _allDocument.postValue(
                                        DataLocal(
                                            file = arrMedia,
                                            folder = arrFolder
                                        )
                                    )
                                }
                            }

                        }

                    } catch (ex: Exception) {
                        //Log.d("HIEN_DATA", documentLocal.toString())
                    }
                }
                cursor.close()
                folders.clear()
            }
            _allDocument.postValue(
                DataLocal(
                    file = arrMedia,
                    folder = arrFolder
                )
            )
        }
    }

    fun getAPKs() {
        val arrMedia = ArrayList<FileModel>()
        val arrFolder = ArrayList<Folder>()
        val folders = HashMap<Long, String>()

        CoroutineScope(Dispatchers.Default).launch {
            var check = 0

            application.contentResolver.query(
                uri,
                projection,
                null,
                null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    try {
                        val idMedia: Long =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                        val nameMedia: String =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                val name = cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                                val index = name.lastIndexOf(".")
                                if (index != -1) {
                                    val nameCut = name.substring(0, index)
                                    nameCut
                                } else {
                                    name
                                }
                            } else cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    MediaStore.Files.FileColumns.TITLE
                                )
                            )

                        val path =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                                "sdcard/" + cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.RELATIVE_PATH
                                    )
                                ) + cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                            else cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                            )

                        val contentUri = Uri.withAppendedPath(uri, "" + idMedia)
                        val folderIdIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
                        val folderNameIndex: Int = cursor.getColumnIndexOrThrow(
                            MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME
                        )
                        val folderId: Long = cursor.getLong(folderIdIndex)
                        val timeModified = File(path).lastModified()

                        val sp = path.split(".")
                        var typeMedia = FileModel.PDF
                        var typeMediaDetail = FileModel.PDF

                        if (!sp.isNullOrEmpty()) {
                            typeMediaDetail = sp[sp.size - 1].trim()
                            typeMedia = if (typeMediaDetail == "apk") FileModel.PDF
                            else continue
                        }

                        if (File(path).exists() && File(path).length() > 1000) {
                            if (!folders.containsKey(folderId)) {
                                val folderName: String = cursor.getString(folderNameIndex)
                                val folder = Folder(folderId, folderName, 0)
                                folders[folderId] = folderName
                                arrFolder.add(folder)
                            } else {
                                arrFolder.find {
                                    it.id == folderId
                                }?.let {
                                    it.size = it.size + 1
                                }
                            }

                            Log.d("hienOCAA", nameMedia.toString())

                            arrMedia.add(
                                FileModel(
                                    id = idMedia,
                                    idFolder = folderId,
                                    name = nameMedia,
                                    uri = contentUri.toString(),
                                    path = path,
                                    timeCreated = timeModified,
                                    type = FileModel.IS_APK,
                                    duration = 0L
                                )
                            )

                            check++
                            if (check == 50) {
                                check = 0
                                withContext(Dispatchers.Main) {
                                    _allAPK.postValue(
                                        DataLocal(
                                            file = arrMedia,
                                            folder = arrFolder
                                        )
                                    )
                                }
                            }

                        }

                    } catch (ex: Exception) {
                        //Log.d("HIEN_DATA", documentLocal.toString())
                    }
                }
                cursor.close()
                folders.clear()
            }
            withContext(Dispatchers.Main) {
                _allAPK.postValue(
                    DataLocal(
                        file = arrMedia,
                        folder = arrFolder
                    )
                )
            }
        }
    }

    fun getZipRar() {
        val arrMedia = ArrayList<FileModel>()
        val arrFolder = ArrayList<Folder>()
        val folders = HashMap<Long, String>()

        CoroutineScope(Dispatchers.Default).launch {
            var check = 0
            val uri = MediaStore.Files.getContentUri("external")
            application.contentResolver.query(
                uri,
                projection,
                null,
                null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    try {
                        val idMedia: Long =
                            cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID))
                        val nameMedia: String =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                val name = cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                                val index = name.lastIndexOf(".")
                                if (index != -1) {
                                    val nameCut = name.substring(0, index)
                                    nameCut
                                } else {
                                    name
                                }
                            } else cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    MediaStore.Files.FileColumns.TITLE
                                )
                            )

                        val path =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                                "sdcard/" + cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.RELATIVE_PATH
                                    )
                                ) + cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                        MediaStore.Files.FileColumns.DISPLAY_NAME
                                    )
                                )
                            else cursor.getString(
                                cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                            )

                        val contentUri = Uri.withAppendedPath(uri, "" + idMedia)
                        val folderIdIndex: Int =
                            cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
                        val folderNameIndex: Int = cursor.getColumnIndexOrThrow(
                            MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME
                        )
                        val folderId: Long = cursor.getLong(folderIdIndex)
                        val timeModified = File(path).lastModified()

                        val sp = path.split(".")
                        var typeMedia = FileModel.PDF
                        var typeMediaDetail = FileModel.PDF

                        if (!sp.isNullOrEmpty()) {
                            typeMediaDetail = sp[sp.size - 1].trim()
                            typeMedia =
                                if (typeMediaDetail == "zip" || typeMediaDetail == "rar") FileModel.PDF
                                else continue
                        }

                        if (File(path).exists() && File(path).length() > 100) {
                            if (!folders.containsKey(folderId)) {
                                val folderName: String = cursor.getString(folderNameIndex)
                                val folder = Folder(folderId, folderName, 0)
                                folders[folderId] = folderName
                                arrFolder.add(folder)
                            } else {
                                arrFolder.find {
                                    it.id == folderId
                                }?.let {
                                    it.size = it.size + 1
                                }
                            }

                            arrMedia.add(
                                FileModel(
                                    id = idMedia,
                                    idFolder = folderId,
                                    name = nameMedia,
                                    uri = contentUri.toString(),
                                    path = path,
                                    timeCreated = timeModified,
                                    type = FileModel.IS_OTHER,
                                    duration = 0L
                                )
                            )

                            check++
                            if (check == CHECK_ITEM_LOADING) {
                                check = 0
                                withContext(Dispatchers.Main) {
                                    _allOther.postValue(
                                        DataLocal(
                                            file = arrMedia,
                                            folder = arrFolder
                                        )
                                    )
                                }
                            }

                        }

                    } catch (ex: Exception) {
                        //Log.d("HIEN_DATA", documentLocal.toString())
                    }
                }
                cursor.close()
                folders.clear()
            }
            withContext(Dispatchers.Main) {
                _allOther.postValue(
                    DataLocal(
                        file = arrMedia,
                        folder = arrFolder
                    )
                )
            }
        }
    }


    fun getApps(
        context: Context,
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            val arrMedia = ArrayList<FileModel>()
            var check = 0
            val packageManager = context.packageManager
            val apps = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
            if (apps.isNotEmpty()) {
                for (p in apps) {
                    try {
                        if (p.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM === ApplicationInfo.FLAG_SYSTEM) {
                            if (null != packageManager.getLaunchIntentForPackage(p.packageName)) {
                                Log.d(
                                    "Hienoc0803", p.applicationInfo.loadLabel(packageManager)
                                        .toString()
                                )
                            }
                        } else {
                            if (null != packageManager.getLaunchIntentForPackage(p.packageName)) {
                                val file = File(p.applicationInfo.publicSourceDir)
                                arrMedia.add(
                                    FileModel(
                                        idFolder = -1,
                                        name = p.applicationInfo.loadLabel(packageManager)
                                            .toString(),
                                        uri = "",
                                        path = "path",
                                        timeCreated = file.lastModified(),
                                        size = file.length(),
                                        type = FileModel.IS_APP,
                                        duration = 0L
                                    )
                                )
                                check++
                                if (check == CHECK_ITEM_LOADING) {
                                    check = 0
                                    withContext(Dispatchers.Main) {
                                        _allApp.postValue(
                                            DataLocal(
                                                file = arrMedia
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            withContext(Dispatchers.Main) {
                _allApp.postValue(
                    DataLocal(
                        file = arrMedia
                    )
                )
            }
        }
    }

//    fun getDuration(context: Context, uri: Uri?): Long {
//        var duration = 0L
//        val cursor: Cursor? = MediaStore.Video.query(
//            context.contentResolver,
//            uri,
//            arrayOf(MediaStore.Video.VideoColumns.DURATION)
//        )
//        if (cursor != null) {
//            cursor.moveToFirst()
//            duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION))
//            cursor.close()
//        }
//        return duration
//    }

    fun File.getMediaDuration(context: Context): Long {
        if (!exists()) return 0
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, Uri.parse(absolutePath))
        val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        retriever.release()

        return duration?.toLongOrNull() ?: 0
    }
}
