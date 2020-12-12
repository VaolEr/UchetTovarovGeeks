from rest_framework.decorators import api_view
from rest_framework.response import Response
from stockapp.api import BasicAPIRequest


@api_view(['GET', 'POST'])
def list_view(request):
    api_r = BasicAPIRequest()
    if request.method == 'GET':
        return Response(api_r.get())

    elif request.method == "POST":
        return Response(api_r.post(request))


@api_view(['GET', 'POST'])
def product_view(request, pk: int):
    if request.method == 'GET':
        pass

    elif request.method == "POST":
        pass

    elif request.method == "DELETE":
        pass


@api_view(['GET', 'POST'])
def create_view(request):
    if request.method == 'GET':
        pass

    elif request.method == "POST":
        pass


