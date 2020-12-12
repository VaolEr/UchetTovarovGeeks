from django.urls import path
from . import views

app_name = 'stockapp'

urlpatterns = [
    path('', views.list_view, name="list_products"),
    path('<pk:int>/', views.product_view, name="view_product"),
    path('create/', views.create_view, name="update_product"),
    path('<pk:int>/update/', views.product_view, name="update_product"),
    path('<pk:int>/delete/', views.product_view, name="update_product"),
]
