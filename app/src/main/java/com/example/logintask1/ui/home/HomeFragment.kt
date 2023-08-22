import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logintask1.R
import com.example.logintask1.data.ListItem
import com.example.logintask1.databinding.FragmentHomeBinding
import com.example.logintask1.ui.adapters.MyListAdapter
import java.util.Random

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var binding: FragmentHomeBinding
    private val adapter = MyListAdapter()
    private var myList: ArrayList<ListItem>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        with(binding) {
            lifecycleOwner = this@HomeFragment
            recyclerViewUsers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerViewUsers.adapter = adapter
            updateAdapter()

            buttonAddUser.setOnClickListener {
                val newList = ArrayList<ListItem>()
                newList.addAll(myList!!)
                val random = Random().nextInt(200)
                val item = ListItem(random, "$random item")
                newList.add(item)
                myList = newList
                adapter.submitList(myList)

            }

        }

    }

    private fun updateAdapter() {
        myList = ArrayList()
        for(i in 0..9) {
            val item = ListItem(i, "Item $i")
            myList?.add(item)
        }
        adapter.submitList(myList)
    }


}