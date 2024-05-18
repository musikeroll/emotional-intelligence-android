package com.roaringapps.codingchallenge.util

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import java.io.File

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
class PdfImage {

    fun bitmap(documentFile: File, pageNumber: Int = 0): Bitmap {

        // Create the page renderer for the PDF document.
        val fileDescriptor = ParcelFileDescriptor.open(
            documentFile,
            ParcelFileDescriptor.MODE_READ_ONLY
        )
        val pdfRenderer = PdfRenderer(fileDescriptor)

        // Open the page to be rendered.
        val page = pdfRenderer.openPage(pageNumber)

        // Render the page to the bitmap.
        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)

        // Close the page when you are done with it.
        page.close()

        // Close the `PdfRenderer` when you are done with it.
        pdfRenderer.close()

        return bitmap
    }
}