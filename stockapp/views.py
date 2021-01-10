from rest_framework.decorators import api_view
from rest_framework.response import Response
from stockapp.api import BasicAPIRequest
from django.shortcuts import render


def index_view(request):
    return render(request, 'stockapp/index.html')


@api_view(['GET', 'POST'])
def product_list_view(request):
    api_r = BasicAPIRequest()
    if request.method == 'GET':
        return Response(api_r.get_products())

    elif request.method == "POST":
        return Response()


@api_view(['GET', 'POST', 'DELETE', 'PUT'])
def product_view(request, pk: int):
    api_r = BasicAPIRequest()
    if request.method == 'GET':     # CRUD - read
        return Response(api_r.get_product(pk))

    elif request.method == "PUT":   # CRUD - update
        return Response(api_r.put_update_product(request, pk))

    elif request.method == "DELETE":    # CRUD - delete
        return Response(api_r.delete_product(pk))


@api_view(['POST'])
def create_view(request):

    api_r = BasicAPIRequest()
    if request.method == "POST":  # CRUD - create
        return Response(api_r.post_create_product(request))


@api_view(['GET'])
def categories_list_view(request):
    api_r = BasicAPIRequest()
    if request.method == 'GET':
        return Response(api_r.get_categories())

    elif request.method == "POST":
        return Response()


@api_view(['GET', 'POST', 'DELETE', 'PUT'])
def categories_view(request, pk: int):
    api_r = BasicAPIRequest()
    if request.method == 'GET':     # CRUD - read
        return Response(api_r.get_category(pk))

    elif request.method == "PUT":   # CRUD - update
        return Response(api_r.put_update_category(request, pk))

    # elif request.method == "DELETE":    # CRUD - delete
    #     return Response(api_r.delete_category(pk))


@api_view(['GET'])
def suppliers_list_view(request):
    api_r = BasicAPIRequest()
    if request.method == 'GET':
        return Response(api_r.get_suppliers())

    elif request.method == "POST":
        return Response()


@api_view(['GET', 'POST', 'DELETE', 'PUT'])
def suppliers_view(request, pk: int):
    api_r = BasicAPIRequest()
    if request.method == 'GET':
        return Response(api_r.get_supplier(pk))

    elif request.method == "PUT":
        return Response(api_r.put_update_supplier(request, pk))


@api_view(['GET', 'POST'])
def storehouses_list_view(request):
    api_r = BasicAPIRequest()
    if request.method == 'GET':
        return Response(api_r.get_storehouses())

    elif request.method == "POST":
        return Response()

@api_view(['GET', 'POST', 'DELETE', 'PUT'])
def storehouses_view(request, pk: int):
    api_r = BasicAPIRequest()
    if request.method == 'GET':
        return Response(api_r.get_storehouse(pk))

    elif request.method == "PUT":
        return Response(api_r.put_update_storehouse(request, pk))


