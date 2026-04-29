package com.example.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context) : View(context) {
    private val board = Array(3) { IntArray(3) { 0 } }
    private var currentTurn = 1
    private var startTurn = 1

    private val paintGrid = Paint().apply {
        color = Color.BLACK
        strokeWidth = 6f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val paintX = Paint().apply {
        color = Color.RED
        strokeWidth = 12f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val paintO = Paint().apply {
        color = Color.BLUE
        strokeWidth = 12f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        val cellW = w / 3f
        val cellH = h / 3f

        canvas.drawLine(cellW, 0f, cellW, h, paintGrid)
        canvas.drawLine(cellW * 2f, 0f, cellW * 2f, h, paintGrid)
        canvas.drawLine(0f, cellH, w, cellH, paintGrid)
        canvas.drawLine(0f, cellH * 2f, w, cellH * 2f, paintGrid)

        for (row in 0..2) {
            for (col in 0..2) {
                val left = col * cellW
                val top = row * cellH
                val margin = cellW * 0.2f

                when (board[row][col]) {
                    1 -> drawX(canvas, left, top, cellW, cellH, margin)
                    2 -> drawO(canvas, left, top, cellW, cellH, margin)
                }
            }
        }
    }

    private fun drawX(canvas: Canvas, left: Float, top: Float, cellW: Float, cellH: Float, margin: Float) {
        canvas.drawLine(left + margin, top + margin, left + cellW - margin, top + cellH - margin, paintX)
        canvas.drawLine(left + cellW - margin, top + margin, left + margin, top + cellH - margin, paintX)
    }

    private fun drawO(canvas: Canvas, left: Float, top: Float, cellW: Float, cellH: Float, margin: Float) {
        val cx = left + cellW / 2f
        val cy = top + cellH / 2f
        val radius = (cellW / 2f) - margin
        canvas.drawCircle(cx, cy, radius, paintO)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            val w = width.toFloat()
            val h = height.toFloat()
            val cellW = w / 3f
            val cellH = h / 3f
            val col = (event.x / cellW).toInt()
            val row = (event.y / cellH).toInt()

            if (row in 0..2 && col in 0..2 && board[row][col] == 0) {

                if (currentTurn % 2 == 1) {
                    board[row][col] = 1//X
                } else {
                    board[row][col] = 2//O
                }
                currentTurn++

                invalidate()
            }
        }
        return true
    }

    fun resetBoard() {
        for (row in 0..2) {
            for (col in 0..2) {
                board[row][col] = 0
            }
        }

        if (startTurn == 1) {
            startTurn = 2
        } else {
            startTurn = 1
        }
        currentTurn = startTurn

        invalidate()
    }
}



