import { Link } from "react-router-dom";

import { Footer } from "@/components/Footer";
import { Header } from "@/components/Header";
import { FeaturedPosts } from "@/components/home/FeaturedPosts";
import { LatestPosts } from "@/components/home/LatestPosts";
import { PopularPosts } from "@/components/home/PopularPosts";
import { MainLayout } from "@/components/layouts/MainLayout";
import { Padding } from "@/components/ui/padding";
import { useEffect, useMemo, useState } from "react";
import { authApi } from "@/utils/api/constants";
import { format } from "date-fns";
import { authentiCate } from "@/utils/functions/function";

export function IndexPage() {
  const [posts, setPosts]: any[] = useState([]);

  authentiCate();
  const getPosts = async () => {
    try {
      const response = await authApi.get("/posts/all");
      setPosts(response.data.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getPosts();
  }, []);

  return (
    <MainLayout>
      <main>
        <Header />
        {/* <LogoutModal /> */}
        {posts.length != 0 ? (
          <Padding className="py-8 grid gap-28 md:grid-cols-2 grid-cols-1 justify-items-center bg-gradient-to-tr from-sky-100 via-white to-slate-100">
            <Link to={`/p/${posts[0].id}`} className="flex flex-col gap-4">
              <img
                src={
                  posts[0].imageUrl
                    ? posts[0].imageUrl
                    : "https://github.blog/wp-content/uploads/2024/07/AI-DarkMode-2-1.png?w=1200"
                }
                alt="Image"
                className="sm:max-w-[600px] w-full max-h-[500px] rounded-xl"
              />
              <h2 className="text-2xl hover:underline cursor-pointer font-bold text-balance max-w-">
                {posts[0].title}
              </h2>
              <p>{posts[0].content}</p>
              <div className="flex gap-2">
                <div>
                  <h2>By</h2>
                </div>
                <p>{posts[0].author.email},</p>
                <p>{format(new Date(posts[0].dateTime), "yyyy MMMM ddd")}</p>
              </div>
            </Link>
            <FeaturedPosts posts={posts} />
          </Padding>
        ) : (
          <Padding>
            <div>
              <p>No posts yet</p>
            </div>
          </Padding>
        )}
        {/* 
        <Padding className="md:py-24 py-6 grid gap-28 md:grid-cols-2 grid-cols-1 justify-items-center ">
          <LatestPosts posts={posts} />
          <PopularPosts posts={posts} />
        </Padding> */}
        <Footer />
      </main>
    </MainLayout>
  );
}
