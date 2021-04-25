package com.example.PizzaApp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private Map<PizzaPlaceForOrders, OrderForCourier> order;
    private ArrayList<PizzaPlaceForOrders> groupList;

    public MyExpandableListAdapter(Context context, Map<PizzaPlaceForOrders, OrderForCourier> order, ArrayList<PizzaPlaceForOrders> groupList) {
        this.context = context;
        this.order = order;
        this.groupList = groupList;
    }

    @Override
    public int getGroupCount() {
        return order.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return order.size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        // return order.get(groupList.get(i).get(i1));
        return order.get(groupList.get(i));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
