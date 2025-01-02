import { NextResponse } from 'next/server';
import type { NextRequest } from 'next/server';
import { getSession } from '@/app/actions/auth/auth-action';

export async function middleware(req: NextRequest) {
  // if (req.nextUrl.pathname.startsWith('/profile')) {
  //   await getSession();
  //   return NextResponse.next();
  // }
  //
  // if (
  //   req.nextUrl.pathname.startsWith('/signin') ||
  //   req.nextUrl.pathname.startsWith('/signup')
  // ) {
  //   const { data } = await getSession();
  //   if (data.isLoggedIn) {
  //     NextResponse.redirect('/');
  //   } else {
  //     NextResponse.next();
  //   }
  // }
}
