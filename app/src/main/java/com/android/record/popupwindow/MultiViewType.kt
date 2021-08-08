package com.android.record.popupwindow

/**
 * RecyclerView实现多种Item布局
 */
interface MultiViewType<in T> {

    /**
     * 实现多种Item布局
     *
     * @param data       Item对应的数据
     * @param position   Item的位置
     * @param totalCount Item的总数
     * @return Item对应的布局类型，使用布局的id进行区分
     */
    fun getLayoutId(data: T, position: Int, totalCount: Int): Int

}