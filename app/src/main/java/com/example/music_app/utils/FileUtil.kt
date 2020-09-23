package com.example.music_app.utils


object FileUtil {
    val PHOTO_EXT = ".jpg"

//    fun createNewImageFile(): File {
//        val storageDir = Injector.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        val file = File.createTempFile(generatedFileName(), PHOTO_EXT, storageDir)
//        return file
//    }
//
//    fun generatedFileName(): String{
//        return UUID.randomUUID().toString()
//    }
//
//    fun getFileNameByUri(uri: Uri): String{
//        var fileName: String? = ""
//        if (uri.scheme.equals("file")) {
//            fileName = uri.lastPathSegment
//        } else {
//            var cursor: Cursor? = null
//            try {
//                cursor = Injector.context.contentResolver.query(
//                    uri, arrayOf(
//                        MediaStore.Images.ImageColumns.DISPLAY_NAME
//                    ), null, null, null
//                )
//                if (cursor != null && cursor.moveToFirst()) {
//                    fileName =
//                        cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))
//                }
//            } finally {
//                if (cursor != null) {
//                    cursor.close()
//                }
//            }
//        }
//        return fileName?:""
//    }
//
//    fun formBase64String(fileUri: Uri): String{
//        var inputData: ByteArray?= null
//        val iStream: InputStream? = Injector.context.contentResolver.openInputStream(fileUri)
//        iStream?.let {
//            inputData = getBytes(iStream)
//        }
//        return Base64.encodeToString(inputData, Base64.NO_WRAP);
//    }
//
//    @Throws(IOException::class)
//    private fun getBytes(inputStream: InputStream): ByteArray? {
//        val byteBuffer = ByteArrayOutputStream()
//        val bufferSize = 1024
//        val buffer = ByteArray(bufferSize)
//        var len = 0
//        while (inputStream.read(buffer).also { len = it } != -1) {
//            byteBuffer.write(buffer, 0, len)
//        }
//        return byteBuffer.toByteArray()
//    }
//
//    fun scalePhoto(bitmap: Bitmap){
//        val newBitmap = Bitmap.createScaledBitmap(bitmap, 1024, 768, false)
//    }
}