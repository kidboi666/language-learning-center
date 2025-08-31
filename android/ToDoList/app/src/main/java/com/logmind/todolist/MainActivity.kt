package com.logmind.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.logmind.todolist.databinding.ActivityMainBinding
import com.logmind.todolist.db.AppDatabase
import com.logmind.todolist.db.ToDoDao
import com.logmind.todolist.db.ToDoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), OnItemLongClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var db : AppDatabase
    private lateinit var todoDao: ToDoDao
    private lateinit var todoList : ArrayList<ToDoEntity>
    private lateinit var adapter : TodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        getAllTodoList()
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }

    private fun getAllTodoList () {
        lifecycleScope.launch {
            todoList = ArrayList(todoDao.getAll())
            withContext(Dispatchers.Main) {
                setRecyclerView()
            }
        }
    }

    private fun setRecyclerView() {
        adapter = TodoRecyclerViewAdapter(todoList, this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun onLongClick(position: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 삭제")
        builder.setMessage("정말 삭제하시겠습니까?")
        builder.setNegativeButton("취소", null)
        builder.setPositiveButton("네") { _, _ ->
            deleteTodo(position)
        }
        builder.show()
    }

    private fun deleteTodo(position: Int) {
        lifecycleScope.launch {
            todoDao.deleteTodo(todoList[position])
            todoList.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this@MainActivity, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}