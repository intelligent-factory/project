module.exports = [
    {
        path: '/dataAnalysis/productionSchedule',
        name: '数据分析报表-生产计划进度',
        component: () => import('@/views/dataAnalysis/ProductionSchedule'),
        meta: {
            title: '生产计划进度-数据分析报表'
        },
    },
    {
        path: '/dataAnalysis/materialStock',
        name: '数据分析报表-物料库存',
        component: () => import('@/views/dataAnalysis/MaterialStock'),
        meta: {
            title: '物料库存-数据分析报表'
        },
    }
]