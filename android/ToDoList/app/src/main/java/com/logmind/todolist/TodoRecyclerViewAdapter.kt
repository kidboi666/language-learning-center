package com.logmind.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logmind.todolist.databinding.ItemTodoBinding
import com.logmind.todolist.db.ToDoEntity

/**
 *
 * [ViewHolder Pattern]
 * 각 뷰 객체를 뷰 홀더에 보관하여 반복적인 메서드 호출을 줄여 속도를 개선하는 패턴
 *
 * [뷰홀더 패턴을 사용하는 이유]
 * 리사이클러뷰는 아이템뷰를 계속해서 재활용한다. 할 일 제목을 바꿔야 할 때 `findViewById(R.id.tv_title)` 과 같이 xml
 * 리소스에 접근하는, 비용이 큰 함수를 사용한다면 성능 저하를 야기할 수 있다. 그 대신에 뷰 홀더 클래스를 만들고 안에 변수를 선언하면
 * 뷰에 즉시 엑세스가 가능해진다. 뷰홀더 내부에서는 태그를 이용해 [findViewById]를 대체한다. 우리는 변수 선언 외에 딱히 해줄 것이 없다.
 *
 */

/**
 *
 * [TodoRecyclerViewAdapter] 클래스는 리사이클러뷰의 어댑터 역할을 함.
 * @param todoList TodoEntity 객체를 담은 리스트
 * @param listener 아이템 롱클릭 리스너
 */
class TodoRecyclerViewAdapter(
    private val todoList: ArrayList<ToDoEntity>,
    private val listener: OnItemLongClickListener
) :
    RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>() {

    /**
     *
     * MyViewHolder 클래스는 뷰 홀더 패턴을 구현하는 내부 클래스임.
     */
    inner class MyViewHolder(binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        val tv_importance = binding.tvImportance
        val tv_title = binding.tvTitle

        /**
         *
         * 뷰 바인딩에서 기본적으로 제공하는 root 변수는 레이아웃의 루트 레이아웃을 의미함.
         * 따라서 이 변수를 사용하여 뷰를 참조할 수 있음.
         */
        val root = binding.root
    }

    /**
     *
     * onCreateViewHolder 메서드는 뷰 홀더를 생성하는 역할을 함.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemTodoBinding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    /**
     *
     * onBindViewHolder 메서드는 뷰 홀더에 데이터를 바인딩하는 역할을 함.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todoData = todoList[position]

        when (todoData.importance) {
            1 -> {
                holder.tv_importance.setBackgroundResource(R.color.red)
            }

            2 -> {
                holder.tv_importance.setBackgroundResource(R.color.yellow)
            }

            3 -> {
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }
        holder.tv_importance.text = todoData.importance.toString()
        holder.tv_title.text = todoData.title
        holder.root.setOnLongClickListener {
            listener.onLongClick(position)
            false
        }
    }

    /**
     *
     * getItemCount 메서드는 어댑터가 보여야 하는 아이템의 개수를 반환함.
     */
    override fun getItemCount(): Int {
        return todoList.size
    }


}