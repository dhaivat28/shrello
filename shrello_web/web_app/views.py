from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
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
		return JsonResponse({'val':'succesful'})
	else:
		return JsonResponse({'val':'failed'})

@csrf_exempt
def email_check(request):
	if request.method=="POST":
		key_email = request.POST['KEY_EMAIL']
		res= auth_model.objects.filter(email = key_email)
		if len(res)>0:
			return JsonResponse({'val':'false'})
		else:
			return JsonResponse({'val':'true'})

@csrf_exempt
def img_test(request):	
	if request.method=="POST":
		img = request.POST['image']
		with open("imageToSave.png", "wb") as fh:
			fh.write(img.decode('base64'))
		#print img
		return JsonResponse({'val':'succesful'})
