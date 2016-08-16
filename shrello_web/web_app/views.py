from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
from django.views.decorators.csrf import csrf_exempt

from models import user_detail as t
def index(request):	
	return HttpResponse("worked")

@csrf_exempt
def signup(request):
	key_f_name = request.POST['KEY_FNAME']
	key_l_name = request.POST['KEY_LNAME']
	key_email = request.POST['KEY_EMAIL']
	key_password = request.POST['KEY_PASS']
	user_details = t(email=key_email,f_name=key_f_name,l_name=key_l_name,password=key_password)
	user_details.save()
	return HttpResponse()