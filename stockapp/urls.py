from django.urls import path
from . import views

app_name = 'stockapp'

urlpatterns = [
    path('', views.list_view, name="list_products"),

    path('create/', views.create_view, name="create_product"),
    path('<int:pk>/update/', views.product_view, name="update_product"),
    path('<int:pk>/delete/', views.product_view, name="update_product"),
    path('<slug:slug>/', views.product_view, name="view_product"),
]
