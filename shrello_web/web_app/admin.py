from django.contrib import admin

# Register your models here.
from .models import user_detail, auth_model

admin.site.register(user_detail)
admin.site.register(auth_model)
