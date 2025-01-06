import { NextRequest, NextResponse } from 'next/server';

export async function middleware(req: NextRequest) {
  if (req.nextUrl.pathname.startsWith('/auth')) {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_SERVER_URL}/auth/session`,
      {
        method: 'GET',
        credentials: 'include',
      },
    );
    const data = await response.json();

    if (data.isLoggedIn) {
      NextResponse.redirect('/');
    } else {
      NextResponse.next();
    }
  }
}
