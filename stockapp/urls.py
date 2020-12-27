from django.urls import path
from . import views

app_name = 'stockapp'

urlpatterns = [
    path('', views.list_view, name="list_products"),

    path('product/create/', views.create_view, name="create_product"),
    path('product/<int:pk>/', views.product_view, name="read_update_delete_product"),
    # path('<slug:slug>/', views.product_view, name="view_product"),
]
