using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Drawing;
using Microsoft.AspNetCore.Identity;

namespace prp.Models
{
    public class User : IdentityUser
    {
        public List<Outlet> List { get; set; }
        public double Rating { get; set; }
        public List<Comment> Comments { get; set; }
    }

    public class Comment
    {
        public Comment ReplyTo { get; set; }
        public User User { get; set; }
        public Outlet Subject { get; set; }
        public double Rating { get; set; }
        public string Text { get; set; }
    }

    public class Category
    {
        public string Name { get; set; }
        public Color Color { get; set; }
    }

    public class Point
    {
        public double Latitude { get; set; }
        public double Longtitude { get; set; }

        public Point() { }
        public Point(double latitude, double longtitude)
        {
            Latitude = latitude;
            Longtitude = longtitude;
        }
    }

    public abstract class Building
    {
        public string Name { get; set; }
        public string Address { get; set; }
        public List<Point> Points { get; set; }
    }

    public class Outlet : Building
    {
        public List<Category> Categories { get; set; }
        public List<Comment> Comments { get; set; }
        public double? Rating { get; set; }
        public Color Color
        {
            get => (Categories.Count == 1) ? Categories.First().Color : ColorCollection.Shared;
            set => Color = value;
        }

        public Outlet()
        {
            Rating = null;
        }

        public Outlet(List<Point> points, Color color)
        {
            Rating = null;
            Points = points;
            Color = color;
        }
    }

    public static class ColorCollection
    {
        public static Color Shared { get; set; }
        private static Dictionary<Category, Color> Values { get; set; }

        public static Color GetColor(Category c) => 
            Values.ContainsKey(c) ? Values[c] : throw new KeyNotFoundException();
    }
}
