package io.marcopolo.benchmark

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    var adjectives: Array<String>? = null
    var colors: Array<String>? = null
    var nouns: Array<String>? = null
    var appData: AppData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adjectives = resources.getStringArray(R.array.adjective)
        colors = resources.getStringArray(R.array.color)
        nouns = resources.getStringArray(R.array.noun)
        appData = AppData(this, adjectives!!, colors!!, nouns!!, findViewById(R.id.list))
    }

    fun run(view: View) {
        appData?.clear()
        appData?.appendRows(10)
    }

    fun runLots(view: View) {
        appData?.clear()
        appData?.appendRows(1000)

    }
    fun add(view: View) {
        appData?.appendRows(1000)
    }

    fun swapRows(view: View) {
        appData?.swapRows()
    }

    fun clear(view: View) {
        appData?.clear()
    }

    fun update(view: View) {
        appData?.update()
    }
}

fun randomHelper (max: Int): Int {
    return (Math.random() * 1000).roundToInt() % max
}

class AppData(
    val ctx: Context,
    val adjectives: Array<String>,
    val colors: Array<String>,
    val nouns: Array<String>,
    val list: LinearLayout
) {
    var rows: ArrayList<Pair<Int, String>> = ArrayList()
    var rowsSoFar = 0
    var selectedRow = 0

    fun createRow (rowId: Int): Pair<Int, String> {
        val a = adjectives[randomHelper(adjectives.size)]
        val c = colors[randomHelper(colors.size)]
        val n = nouns[randomHelper(nouns.size)]
        return Pair(rowId, "$a $c $n")
    }

    fun appendRows(count: Int) {
        for (i in 1 .. count) {
            val rowData = createRow(rowsSoFar++)
            rows.add(rowData)
            list.addView(RowView(ctx, rowData, View.OnClickListener{
                Log.d("row", "Selected ${rowData.first}")
                select(rows.indexOf(rowData))
            }, View.OnClickListener{
                remove(rows.indexOf(rowData))
            }))
        }
    }

    fun select(idx: Int) {
        // Old Selected
        list.getChildAt(selectedRow).apply {
            setBackgroundColor(Color.TRANSPARENT)
        }

        // New Selected
        list.getChildAt(idx).apply {
            setBackgroundColor(Color.RED)
        }

        selectedRow = idx
    }

    fun remove(idx: Int) {
        list.removeViewAt(idx)
        rows.removeAt(idx)
    }

    fun update() {
        for (i in 0 until rows.size step 10) {
            var row = rows[i]
            rows[i] = Pair(row.first, "${row.second} !!!")
            row = rows[i]
            list.getChildAt(i).apply {
                (this as RowView).setText(row.second)
            }
        }
    }

    fun clear() {
        rowsSoFar = 0
        list.removeAllViews()
        rows = ArrayList()
    }

    fun swapRows() {
        if (rows.size > 998) {
            val row = rows[1]
            rows[1] = rows[998]
            rows[998] = row

            val otherRowView = list.getChildAt(998)
            list.removeViewAt(998)

            val rowView = list.getChildAt(1)
            list.removeViewAt(1)

            list.addView(otherRowView, 1)
            list.addView(rowView, 998)
        }
    }
}

class RowModel(initialVal: String) : LiveData<String>() {
    init {
        value = initialVal
    }

    fun bump() {
        value = "$value !!!"
    }
}

class RowView(context: Context, rowData: Pair<Int, String>, onSelect: OnClickListener, onRemove: OnClickListener) : LinearLayout(context) {
    private var mainText: TextView

    init {
        val (rowNumber, v) = rowData
        addView(TextView(context).apply {
            text = "$rowNumber"
        })

        mainText = TextView(context).apply {
            text = v
            setOnClickListener(onSelect)
        }

        addView(mainText)
        addView(TextView(context).apply {
            text = "X"
            setOnClickListener(onRemove)
        })
    }

    fun setText(s: String) {
       mainText.text = s
    }
}
