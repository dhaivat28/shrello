# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2016-08-22 09:15
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('web_app', '0007_delete_auth_model'),
    ]

    operations = [
        migrations.CreateModel(
            name='auth_model',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('email', models.CharField(max_length=200)),
                ('password', models.CharField(max_length=200)),
            ],
        ),
    ]
