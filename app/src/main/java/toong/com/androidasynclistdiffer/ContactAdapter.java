package toong.com.androidasynclistdiffer;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final String TAG = getClass().getSimpleName();
    private final AsyncListDiffer<Contact> mDiffer = new AsyncListDiffer(this, DIFF_CALLBACK);

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public void submitList(List<Contact> list) {
        mDiffer.submitList(list);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder " + position);
        Contact contact = mDiffer.getCurrentList().get(position);
        holder.name.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());
    }

    private static final DiffUtil.ItemCallback<Contact> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<Contact>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull Contact oldUser, @NonNull Contact newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return true;
        }

        @Override
        public boolean areContentsTheSame(
                @NonNull Contact oldUser, @NonNull Contact newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.compareTo(newUser) == 0;
        }
    };

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name, phoneNumber;

        ContactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_contact_name);
            phoneNumber = itemView.findViewById(R.id.tv_contact_phone_number);
        }
    }
}
