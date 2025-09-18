import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:future_provider/pages/users/user_detail_page.dart';
import 'package:future_provider/pages/users/users_providers.dart';

import '../../models/user.dart';

class UserListPage extends ConsumerWidget {
  const UserListPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final userList = ref.watch(userListProvider);
    print(userList.toString());
    print(
      'isLoading: ${userList.isLoading}, isRefreshing: ${userList.isRefreshing}, isReloading: ${userList.isReloading}',
    );
    print('hasValue: ${userList.hasValue}, hasError: ${userList.hasError}');

    return Scaffold(
      appBar: AppBar(
        title: const Text('User List'),
        actions: [
          IconButton(
            onPressed: () {
              ref.invalidate(userListProvider);
            },
            icon: const Icon(Icons.refresh),
          ),
        ],
      ),
      body: userList.when(
        data: (users) => _buildUserList(context, ref, users),
        error: (error, stackTrace) => Center(child: Text('Error: $error')),
        loading: () => const Center(child: CircularProgressIndicator()),
      ),
    );
  }

  Widget _buildUserList(BuildContext context, WidgetRef ref, List<User> users) {
    return RefreshIndicator(
      onRefresh: () => ref.refresh(userListProvider.future),
      color: Colors.red,
      child: ListView.separated(
        itemCount: users.length,
        separatorBuilder: (context, index) => const Divider(),
        itemBuilder: (context, index) {
          final user = users[index];
          return ListTile(
            leading: CircleAvatar(child: Text(user.id.toString())),
            title: Text(user.name),
            onTap: () {
              Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (_) => UserDetailPage(userId: user.id),
                ),
              );
            },
          );
        },
      ),
    );
  }
}
