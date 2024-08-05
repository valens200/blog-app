import React, { useState } from "react";
import { Menu, Plus } from "lucide-react";
import { Link, useNavigate } from "react-router-dom";

import { Button } from "./ui/button";
import { Padding } from "./ui/padding";
import { useScroll } from "@/hooks/use-scroll";
import { staticUser } from "./data";

interface HeaderProps {}

export const Header: React.FC<HeaderProps> = () => {
  const scrolled = useScroll(50);
  const [showMenu, setShowMenu] = useState(false);
  const [loggedIn, setLoggedIn] = useState(true);
  const navigate = useNavigate();
  const LogOut = async () => {
    try {
      localStorage.removeItem("token");
      navigate("/");
    } catch (error) {
      console.log(error);
    }
  };
  const toggleMenu = () => {
    setShowMenu(!showMenu);
  };

  return (
    <Padding
      className={`${
        scrolled
          ? "backdrop-blur-lg py-2 border-b border-b-border fixed"
          : "bg-primary py-8"
      } text-foreground flex flex-col gap-6 top-0 w-full`}
    >
      <div className="flex w-full justify-between">
        <Link
          to={`/`}
          className={`${
            !scrolled ? "text-primary-foreground" : "text-slate-950"
          } text-2xl font-bold`}
        >
          <span className="text-3xl text-muted-foreground font-normal">/</span>
          Blog
        </Link>
        {!loggedIn && (
          <div className="sm:flex gap-5 items-center text-muted-foreground hidden">
            <Button
              variant={`outline`}
              className="text-primary"
              onClick={() => navigate("/register")}
            >
              Sign up for free
            </Button>
            <Button onClick={() => navigate("/login")}>Sign in</Button>
          </div>
        )}
        {loggedIn && (
          <div className="">
            <div className="flex items-center px-5">
              <div className="flex-shrink-0">
                <img
                  className="h-10 w-10 rounded-full"
                  src={staticUser.traineeImageUrl}
                  alt=""
                />
              </div>
              <div className="ml-3">
                <div className="text-base font-medium leading-none text-white">
                  {staticUser.name}
                </div>
                <div className="text-sm font-medium leading-none text-gray-400">
                  {staticUser.email}
                </div>
              </div>
              <button
                onClick={() => LogOut()}
                type="button"
                className="relative rotate-90 ml-auto flex-shrink-0 rounded-full bg-gray-800 p-1 text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
              >
                <span className="absolute -inset-1.5" />
                <span className="sr-only">View notifications</span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth={1.5}
                  stroke="currentColor"
                  className="size-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M3 16.5v2.25A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75V16.5m-13.5-9L12 3m0 0 4.5 4.5M12 3v13.5"
                  />
                </svg>
              </button>
            </div>
          </div>
        )}
        <div className="sm:hidden flex items-center gap-4 text-primary-foreground">
          {!showMenu ? (
            <Menu
              className={`cursor-pointer h-7 w-7 ${
                scrolled && "text-slate-950"
              }`}
              onClick={toggleMenu}
            />
          ) : (
            <Plus
              className={`transform rotate-45 h-7 w-7 cursor-pointer ${
                scrolled && "text-slate-950"
              }`}
              onClick={toggleMenu}
            />
          )}
        </div>
      </div>

      {showMenu ? (
        <div className="relative z-30 flex gap-2 flex-col mt-5 overflow-y-auto">
          <Button
            variant={`outline`}
            className="text-primary bg-gradient-to-br from-primary to-secondary via-muted"
            onClick={() => navigate("/register")}
          >
            Sign up for free
          </Button>
          <Button onClick={() => navigate("/login")}>Sign in</Button>
        </div>
      ) : null}
    </Padding>
  );
};
