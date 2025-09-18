import 'package:flutter/material.dart';

class UserDetailPageRow extends StatelessWidget {
  final String userInfo;
  final IconData icon;
  const UserDetailPageRow({
    super.key,
    required this.userInfo,
    required this.icon,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Icon(icon),
        const SizedBox(width: 10),
        Text(userInfo, style: Theme.of(context).textTheme.titleLarge),
      ],
    );
  }
}
