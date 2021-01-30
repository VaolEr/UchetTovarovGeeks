from django.urls import path
from . import views

app_name = 'stockapp'

urlpatterns = [
    # ?page=0&size=20
    path('', views.product_list_view, name="list_products"),

    path('product/create/', views.create_view, name="create_product"),
    path('product/<int:pk>/', views.product_view, name="read_update_delete_product"),
    # path('<slug:slug>/', views.product_view, name="view_product"),
    path('categories/', views.categories_list_view, name="list_categories"),
    path('categories/<int:pk>/', views.categories_view, name="read_categoriy"),
    path('suppliers/', views.suppliers_list_view, name="list_suppliers"),
    path('suppliers/<int:pk>/', views.suppliers_view, name="read_supplier"),
    path('storehouses/', views.storehouses_list_view, name="list_storehouses"),
    path('storehouses/<int:pk>/', views.storehouses_view, name="read_storehouse"),
    path('units/', views.units_list_view, name="list_units"),
    path('units/<int:pk>/', views.units_view, name="read_unit"),
    path('login', views.login, name='login')
]
