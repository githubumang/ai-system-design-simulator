import { Link } from "react-router-dom"
import { Network, Code2 } from "lucide-react"

const quickLinks = [
  { label: "Home", to: "/" },
  { label: "Questions", to: "/questions" },
  { label: "How It Works", to: "/#how-it-works" },
  { label: "Start Practicing", to: "/questions" },
]

export function SiteFooter() {
  return (
    <footer className="border-t border-border/60 bg-background">
      <div className="mx-auto max-w-[1280px] px-4 py-12 sm:px-6 lg:px-8">
        <div className="flex flex-col gap-10 md:flex-row md:items-start md:justify-between">
          <div className="max-w-sm">
            <div className="flex items-center gap-2.5">
              <span className="flex h-9 w-9 items-center justify-center rounded-xl bg-primary text-primary-foreground">
                <Network className="h-5 w-5" aria-hidden="true" />
              </span>
              <span className="text-sm font-semibold tracking-tight">AI System Design Simulator</span>
            </div>
            <p className="mt-4 text-sm leading-relaxed text-muted-foreground">
              Practice real-world system design interviews and get AI-powered feedback to land your next role.
            </p>
          </div>

          <div className="flex flex-col gap-8 sm:flex-row sm:gap-16">
            <div>
              <h3 className="text-xs font-semibold uppercase tracking-wider text-muted-foreground">Quick Links</h3>
              <ul className="mt-4 flex flex-col gap-3">
                {quickLinks.map((link) => (
                  <li key={link.label}>
                    <Link
                      to={link.to}
                      className="text-sm text-muted-foreground transition-colors hover:text-foreground"
                    >
                      {link.label}
                    </Link>
                  </li>
                ))}
              </ul>
            </div>

            <div>
              <h3 className="text-xs font-semibold uppercase tracking-wider text-muted-foreground">Connect</h3>
              <ul className="mt-4 flex flex-col gap-3">
                <li>
                  <a
                    href="https://github.com"
                    target="_blank"
                    rel="noopener noreferrer"
                    className="inline-flex items-center gap-2 text-sm text-muted-foreground transition-colors hover:text-foreground"
                  >
                    <Code2 className="h-4 w-4" aria-hidden="true" />
                    GitHub
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div className="mt-10 border-t border-border/60 pt-6">
          <p className="text-xs text-muted-foreground">
            &copy; {new Date().getFullYear()} AI System Design Simulator. All rights reserved.
          </p>
        </div>
      </div>
    </footer>
  )
}
