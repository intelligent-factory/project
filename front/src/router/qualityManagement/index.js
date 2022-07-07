module.exports = [
    //产品质量管理
    {
        path:'/qualityManagement/monitorManagementComponent',
        name:'monitorManagementComponent',
        component:() => import('@/views/qualityManagement/monitorManagementComponent'),
        meta:{
            title: '质检录入-产品质量管理',
        }
    },
    {
        path:'/qualityManagement/defectManagementComponent',
        name:'defectManagementComponent',
        component:() => import('@/views/qualityManagement/defectManagementComponent'),
        meta:{
            title: '瑕疵管理-产品质量管理',
        }
    },
    {
        path:'/qualityManagement/qualityManagementComponent',
        name:'qualityManagementComponent',
        component:() => import('@/views/qualityManagement/qualityManagementComponent'),
        meta:{
            title: '品质管理-产品质量管理',
        }
    },
]
