from __future__ import unicode_literals

from django.db import models

# Create your models here.
class user_detail(models.Model):	
	email=models.CharField(max_length=200)
	f_name=models.CharField(max_length=200)
	l_name=models.CharField(max_length=200)	
	password=models.CharField(max_length=200)
