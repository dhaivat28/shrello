# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2016-09-04 08:54
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('web_app', '0008_auth_model'),
    ]

    operations = [
        migrations.AlterField(
            model_name='user_detail',
            name='profile_picture',
            field=models.ImageField(blank=True, null=True, upload_to='profile_pictures/'),
        ),
    ]