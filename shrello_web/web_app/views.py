from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
from django.views.decorators.csrf import csrf_exempt

from models import user_detail,auth_model
def index(request):
	return HttpResponse("worked")

@csrf_exempt
def signup(request):
	if request.method=="POST":
		key_full_name = request.POST['KEY_FULLNAME']
		key_email = request.POST['KEY_EMAIL']
		key_password = request.POST['KEY_PASS']
		user_detail_object = user_detail(email=key_email,full_name=key_full_name)
		user_detail_object.save()
		auth_model_object = auth_model(email=key_email,password=key_password)
		auth_model_object.save()
		return HttpResponse("succesful")
	else:
		return HttpResponse("fuck off you hacker!")

@csrf_exempt
def email_check(request):
	if request.method=="POST":
		key_email = request.POST['KEY_EMAIL']
		res= auth_model.objects.filter(email = key_email)
		if len(res)>0:
			return HttpResponse("false")
		else:
			return HttpResponse("true")
