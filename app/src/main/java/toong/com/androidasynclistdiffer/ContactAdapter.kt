package toong.com.androidasynclistdiffer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder?>() {
    private val TAG = javaClass.simpleName
    val mDiffer: AsyncListDiffer<Contact> = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<Contact>) {
        Log.i(TAG, "submit list " + list.joinToString { it.name })
        mDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = mDiffer.currentList[position]
        Log.i(TAG, "onBindViewHolder $position " + contact.name)
        holder.name.text = contact.name
        holder.phoneNumber.text = contact.phoneNumber
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_contact_name)
        var phoneNumber: TextView = itemView.findViewById(R.id.tv_contact_phone_number)

    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Contact> =
            object : DiffUtil.ItemCallback<Contact>() {
                override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }
}