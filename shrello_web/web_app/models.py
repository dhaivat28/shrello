from __future__ import unicode_literals

from django.db import models

# Create your models here.
class auth_model(models.Model):
	email=models.CharField(max_length=200)
	password=models.CharField(max_length=200)
class user_detail(models.Model):
	id = models.AutoField(primary_key=True)
	email=models.CharField(max_length=200)
	full_name=models.CharField(max_length=200)
	location=models.CharField(max_length=200, null=True, blank=True)
	profile_picture=models.ImageField(upload_to='profile_pictures/', null=True, blank=True)
	personal_note=models.TextField(null=True, blank=True)

class org_detail(models.Model):
	id = models.AutoField(primary_key=True)
	name=models.CharField(max_length=200)
	add=models.TextField()
	email=models.CharField(max_length=200)
	url=models.CharField(max_length=200)
	phone=models.CharField(max_length=200)
	username=models.CharField(max_length=200)
	password=models.CharField(max_length=200)
