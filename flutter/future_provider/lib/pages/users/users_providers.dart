import 'package:riverpod_annotation/riverpod_annotation.dart';

import '../../models/user.dart';
import '../../providers/dio_provider.dart';

part 'users_providers.g.dart';

// final userListProvider = FutureProvider.autoDispose((ref) async {
//   ref.onDispose(() {
//     print('[userListProvider] dispose');
//   });
//   final response = await ref.watch(dioProvider).get('/users');
//   final userList = response.data;
//   final users = [for (final user in userList) User.fromJson(user)];
//   return users;
// });

@riverpod
FutureOr<List<User>> userList(Ref ref) async {
  ref.onDispose(() {
    print('[userListProvider] dispose');
  });
  final response = await ref.watch(dioProvider).get('/users');
  final userList = response.data;
  final users = [for (final user in userList) User.fromJson(user)];
  return users;
}

// final userDetailProvider = FutureProvider.family<User, int>((ref, id) async {
//   ref.onDispose(() {
//     print('[userDetailProvider] dispose');
//   });
//   final response = await ref.watch(dioProvider).get('/users/$id');
//   final user = User.fromJson(response.data);
//   return user;
// });

@riverpod
FutureOr<User> userDetail(Ref ref, int id) async {
  ref.onDispose(() {
    print('[userDetailProvider] dispose');
  });
  final response = await ref.watch(dioProvider).get('/users/$id');
  ref.keepAlive();
  final user = User.fromJson(response.data);
  return user;
}

@Riverpod(keepAlive: true)
FutureOr<int> returnOne(Ref ref) {
  ref.keepAlive();
  return Future.value(1);
}
