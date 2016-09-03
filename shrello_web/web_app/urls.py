from django.conf.urls import url

from . import views
from . import models
urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^signup/', views.signup, name='signup'),
    url(r'^email_check/', views.email_check, name='email_check'),
    url(r'^img_test/', views.img_test, name='img_test'),
]
