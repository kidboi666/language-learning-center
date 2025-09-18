import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:future_provider/pages/users/user_detail_page_row.dart';
import 'package:future_provider/pages/users/users_providers.dart';

class UserDetailPage extends ConsumerWidget {
  final int userId;
  const UserDetailPage({super.key, required this.userId});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userDetail = ref.watch(userDetailProvider(userId));
    return Scaffold(
      appBar: AppBar(title: const Text('User Detail')),
      body: userDetail.when(
        data: (user) => RefreshIndicator(
          onRefresh: () async => ref.refresh(userDetailProvider(userId)),
          child: ListView(
            padding: const EdgeInsets.symmetric(vertical: 40, horizontal: 20),
            children: [
              Text(
                user.name,
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              const Divider(),
              UserDetailPageRow(
                userInfo: user.username,
                icon: Icons.account_circle,
              ),
              const SizedBox(height: 10),
              UserDetailPageRow(userInfo: user.email, icon: Icons.email),
              const SizedBox(height: 10),
              UserDetailPageRow(userInfo: user.phone, icon: Icons.phone),
              const SizedBox(height: 10),
              UserDetailPageRow(userInfo: user.website, icon: Icons.web),
            ],
          ),
        ),
        error: (error, stackTrace) => Center(child: Text('Error: $error')),
        loading: () => const Center(child: CircularProgressIndicator()),
      ),
    );
  }
}
